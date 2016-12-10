package kona.hibernate.exporter;

import kona.model.domain.address.Address;
import kona.model.domain.customer.Customer;
import kona.model.domain.orderhandling.DeliveryOrder;
import kona.model.domain.orderhandling.OrderLine;
import kona.model.domain.product.Product;
import kona.model.domain.user.User;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.EnumSet;

public class HibernateDDLExporter {
    private static Logger logger = LoggerFactory.getLogger(HibernateDDLExporter.class);

    private String dialect;
    private String[] entityPackages;
    private String directory;
    private String fileName;

    public HibernateDDLExporter dialect(String dialect) {
        this.dialect = dialect;
        return this;
    }

    public HibernateDDLExporter fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public HibernateDDLExporter entityPackages(String... entityPackages) {
        this.entityPackages = entityPackages;
        return this;
    }

    public void export() throws Exception {
        File exportFile;
        if (directory != null) {
            final File targetDirectory = new File(directory);
            targetDirectory.mkdirs();
            exportFile = new File(targetDirectory, fileName);
        } else {
            exportFile = new File(fileName);
        }
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySetting(AvailableSettings.DIALECT, dialect)
                .build();

        MetadataImplementor metadata = (MetadataImplementor) createMetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setOutputFile(exportFile.getAbsolutePath());
        schemaExport.setDelimiter(";");
        schemaExport.setFormat(true);
        schemaExport.create(EnumSet.of(TargetType.SCRIPT), metadata);

        ((StandardServiceRegistryImpl) serviceRegistry).destroy();
        logger.info(exportFile.getAbsolutePath());
    }

    public HibernateDDLExporter directory(String directory) {
        this.directory = directory;
        return this;
    }

    private MetadataSources createMetadataSources(ServiceRegistry serviceRegistry) {
        MetadataSources sources = new MetadataSources(serviceRegistry);

        // Note, this could be done with reflection too

        sources.addAnnotatedClass(Address.class);
        sources.addAnnotatedClass(Product.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Customer.class);
        sources.addAnnotatedClass(OrderLine.class);
        sources.addAnnotatedClass(DeliveryOrder.class);

        return sources;
    }

    public static void main(String[] args) throws Exception {
        new HibernateDDLExporter()
                .dialect("org.hibernate.dialect.PostgreSQLDialect")
                .directory("build")
                .fileName("create_database.sql")
                .export();
    }
}
