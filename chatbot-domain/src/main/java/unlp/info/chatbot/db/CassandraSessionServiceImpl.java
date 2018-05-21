package unlp.info.chatbot.db;

import com.datastax.driver.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CassandraSessionServiceImpl implements InitializingBean, CassandraSessionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CassandraSessionServiceImpl.class);

  //@Value("${chatbot.cassandra.datasource.url}")
  // TODO: LN agregar environment.properties
  @Value("localhost")
  private String nodes;

  private Session session;

  private Cluster cluster;

  public Session cassandraSession() {
    return session;
  }

  public void connect(String nodes) {
    String[] bufferedNodes = nodes.split(",");
    this.cluster = Cluster.builder().addContactPoints(bufferedNodes).withSocketOptions(new SocketOptions()
        .setReadTimeoutMillis(300000)).build();
    Metadata metadata = this.cluster.getMetadata();
    LOGGER.info("Connected to cluster: {}", metadata.getClusterName());
    for (Host host : metadata.getAllHosts()) {
      LOGGER.info("Datatacenter: {}; Host: {}; Rack: {}", host.getDatacenter(), host.getAddress(), host.getRack());
    }
  }

  public String getNodes() {
    return nodes;
  }

  public void setNodes(String nodes) {
    this.nodes = nodes;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.connect(this.nodes);
    this.session = this.cluster.connect();
  }
}
