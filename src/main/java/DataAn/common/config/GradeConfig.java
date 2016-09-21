package DataAn.common.config;


public enum GradeConfig {
	
	GRADE_1S {
		@Override
		public String getName() {
			
			return "1s";
		}

		@Override
		public long getValue() {
			return 1000;
		}
	},
	GRADE_5S {
		@Override
		public String getName() {
			
			return "5s";
		}

		@Override
		public long getValue() {
			return 5*1000;
		}
	},
	GRADE_30S {
		@Override
		public String getName() {
			
			return "30s";
		}

		@Override
		public long getValue() {
			return 30*1000;
		}
	},
	GRADE_60S {
		@Override
		public String getName() {
			
			return "60s";
		}

		@Override
		public long getValue() {
			return 60*1000;
		}
	},
	GRADE_5M {
		@Override
		public String getName() {
			
			return "5m";
		}

		@Override
		public long getValue() {
			return 5*60*1000;
		}
	},
	GRADE_15M {
		@Override
		public String getName() {
			
			return "15m";
		}

		@Override
		public long getValue() {
			return 15*60*1000;
		}
	},
	GRADE_30M {
		@Override
		public String getName() {
			
			return "30m";
		}

		@Override
		public long getValue() {
			return 30*60*1000;
		}
	},
	GRADE_1H {
		@Override
		public String getName() {
			
			return "1h";
		}

		@Override
		public long getValue() {
			return 60*60*1000;
		}
	},
	GRADE_6H {
		@Override
		public String getName() {
			
			return "6h";
		}

		@Override
		public long getValue() {
			return 6*60*60*1000;
		}
	},
	GRADE_12H {
		@Override
		public String getName() {
			
			return "12h";
		}

		@Override
		public long getValue() {
			return 12*60*60*1000;
		}
	},
	GRADE_1D {
		@Override
		public String getName() {
			
			return "1d";
		}

		@Override
		public long getValue() {
			return 24*60*60*1000;
		}
	};
	public abstract String getName();
	public abstract long getValue();
}
