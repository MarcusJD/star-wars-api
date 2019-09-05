package sw.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration
{
	@Autowired
    private Environment env;
	
	@Override
	public MongoClient mongoClient( ) 
	{
		return new MongoClient( env.getProperty( "mongo.host" ), Integer.parseInt( env.getProperty( "mongo.port" ) ) );
	}

	@Override
	protected String getDatabaseName( )
	{
		return env.getProperty( "mongo.database" );
	}
	
	protected String getMappingBasePackage( )
	{
        return "sw.com.api.model";
	}

	public @Bean
	MongoDbFactory mongoDbFactory( )
	{
		return new SimpleMongoDbFactory( new MongoClient( ), "starwars" );
	}

	public @Bean
	MongoTemplate mongoTemplate( ) throws Exception
	{
		MongoTemplate mongoTemplate = new MongoTemplate( mongoDbFactory( ) );
				
		return mongoTemplate;
	}
}
