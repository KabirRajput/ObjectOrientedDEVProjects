package loggingDemo.logDemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Log4jThread;

public class App 
{
	static Logger log = LogManager.getLogger(App.class);
	public void run()
	{
		for(int i = 0; i< 1000; i++)
		{
			switch(1%3) {

			case 0:
				log.warn("i= " + i);
				break;
			case 1:
				log.error("i= " + i);
				break;
			case 2:
				log.fatal("i= " + i);
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		LogThread t1 = new Log4jThread();
		LogThread t2 = new Log4jThread();
		
		t1.start();
		t2.start();
	}
	

}
