package DataAn.common.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class InitDataListener implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("InitDataListener..." + event);			
		if(event.getApplicationContext().getParent() == null){
			System.out.println("加载一次 InitDataListener..." + event);
		}
	}

	

}
