# springBatchDB2DB

Spring Boot application with Spring Batch to read data from one data source like MYSQL/ORACLE or custom or DB virtualization tool 
like DENODO.
the datasource can be configured as primary and secondary to serve the purpose of source DB, where the Data has to be read from and
the target datasource, where the data will be inserted to.
Application build tool is Maven.

The Oracle or any other database driver (which is not open-source) has to be downloaded and installed via the 
'mvn install command' like
mvn install:install-file -Dfile=/filelocation/denodo-vdp-jdbcdriver.jar -DgroupId=com.denodo -DartifactId=denodo-vdp-jdbcdriver -Dversion=6.0.3 -Dpackaging=jar 
after successfull installation in pom.xml, we need to specify the dependecy like:
<dependency>
			<groupId>com.denodo</groupId>
			<artifactId>denodo-vdp-jdbcdriver</artifactId>
			<version>6.0.3</version>
</dependency>
