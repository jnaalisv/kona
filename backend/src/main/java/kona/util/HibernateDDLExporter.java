package kona.util;

import kona.domain.address.Address;
import kona.domain.customer.Customer;
import kona.domain.orderhandling.OrderLine;
import kona.domain.orderhandling.PurchaseOrder;
import kona.domain.product.Product;
import kona.domain.user.User;
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
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

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

    public HibernateDDLExporter directory(String directory) {
        this.directory = directory;
        return this;
    }

    public void export() {
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

        MetadataImplementor metadata = (MetadataImplementor) createMetadataSources(serviceRegistry)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(new SpringImplicitNamingStrategy())
                .applyPhysicalNamingStrategy(new SpringPhysicalNamingStrategy())
                .build();


        new SchemaExport()
                .setOutputFile(exportFile.getAbsolutePath())
                .setDelimiter(";")
                .setFormat(true)
                .create(EnumSet.of(TargetType.SCRIPT), metadata);

        ((StandardServiceRegistryImpl) serviceRegistry).destroy();
        logger.info(exportFile.getAbsolutePath());
    }

    private MetadataSources createMetadataSources(ServiceRegistry serviceRegistry) {

        // Note, this could be done with reflection too

        return new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(OrderLine.class)
                .addAnnotatedClass(PurchaseOrder.class);
    }

    public static void main(String[] args) {
        new HibernateDDLExporter()
                .dialect("org.hibernate.dialect.PostgreSQLDialect")
                .directory("build")
                .fileName("create_database.sql")
                .export();
    }
}
