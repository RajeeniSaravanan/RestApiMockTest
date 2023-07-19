package api.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jutility 
{

	private Logger log = null;
	private static Log4jutility ob= null;
	
	private Log4jutility()
	{
		
	}
	
	public static Log4jutility getInstance()
	{
		if(ob==null) 
		{
			ob =  new Log4jutility();
		}
		return ob;
	}
	
	public Logger getLogger()
	{
		log= LogManager.getLogger(Log4jutility.class);
		return log;
		
	}
}
