package burukeyou.search.adapter.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CanalTask implements Runnable, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private CanalConnector connector;

    @Override
    @Scheduled(fixedRate = 100) // 每隔100秒
    public void run() {
        long batchId = -1 ;
        try {
            int batchSize = 1000;
            // 获取指定数量的数据
            Message message = connector.getWithoutAck(batchSize);
            batchId = message.getId(); // 消息批次id
            int size = message.getEntries().size();
            if (batchId != -1 || size > 0) {
                List<CanalEntry.Entry> entries = message.getEntries();
                entries.forEach(e->{
                    if (e.getEntryType() == CanalEntry.EntryType.ROWDATA){
                            publicCanalEvent(e);
                    }
                });
            }
            connector.ack(batchId);
        } catch (CanalClientException e) {
            e.printStackTrace();
            connector.rollback(batchId);
        }

    }

    private void publicCanalEvent(CanalEntry.Entry entry) {
        try {
            CanalEntry.EntryType entryType = entry.getEntryType();
            String database = entry.getHeader().getSchemaName();
            String tableName = entry.getHeader().getTableName();
            CanalEntry.RowChange rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());


            rowChage.getRowDatasList().forEach(e->{
                List<CanalEntry.Column> afterColumnsList = e.getAfterColumnsList();

                CanalEntry.Column idColumn = afterColumnsList.stream().filter(column ->
                        column.getIsKey() && "id".equals(column.getName())).findFirst().orElse(null);

                Map<String, String> columnMap = buildColumnToMap(afterColumnsList);

                indexToEs(columnMap,database,tableName);
            });

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return;
        }
    }

    //
    private void indexToEs(Map<String, String> columnMap, String database, String tableName) {
        // todo 同步到es
    }

    private Map<String,String> buildColumnToMap(List<CanalEntry.Column> columns){
        Map<String,String> columnMap = new HashMap<>();
        columns.forEach(column -> {
            if (column == null)
                return;

            columnMap.put(column.getName(),column.getValue());
        });
        return columnMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
    }
}
