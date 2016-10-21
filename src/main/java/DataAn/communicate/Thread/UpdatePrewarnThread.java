package DataAn.communicate.Thread;

import DataAn.prewarning.service.IPrewarningService;

public class UpdatePrewarnThread extends Thread {

	private IPrewarningService prewarningService;

	public UpdatePrewarnThread(IPrewarningService prewarningService) {
		this.prewarningService = prewarningService;
	}

	@Override
	public void run() {
		try {
			prewarningService.addWarningLogFromMongo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IPrewarningService getPrewarningService() {
		return prewarningService;
	}

	public void setPrewarningService(IPrewarningService prewarningService) {
		this.prewarningService = prewarningService;
	}

}
