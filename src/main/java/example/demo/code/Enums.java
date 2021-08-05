package example.demo.code;


/**
 *
 * @author LR<br>
 * @date 2021/7/2 15:58
 */
public enum Enums {

	ONE(1,"ONE", withType(Type.SYSTEM, Type.EMAIL, Type.PHONE)),

	TWO(2,"TWO", withType(Type.SYSTEM)),

	THREE(3,"THREE", withType(Type.SYSTEM, Type.EMAIL)),

	FOUR(4,"FOUR", withType(Type.SYSTEM)),

	FIVE(5,"FIVE", withType(Type.SYSTEM, Type.EMAIL, Type.PHONE)),

	SIX(6,"SIX", withType(Type.SYSTEM, Type.EMAIL, Type.PHONE));// 企账户

	enum Type {
		SYSTEM(1),
		EMAIL(2),
		PHONE(3);

		static final int TOTAL = Type.values().length;
		final int position;
		Type(int position) {
			this.position = position;
		}
		private int mask() {
			return 1 << (TOTAL - position);
		}
	}

	private static int withType(Type... types) {
		int mask = 0;
		for (Type type : types) {
			mask |= type.mask();
		}
		return mask;
	}

	private final int type;
	private final String des;
	private final int initParam;


	Enums(int type, String des, int initParam) {
		this.type = type;
		this.des = des;
		this.initParam = initParam;
	}

	public int getType() {
		return type;
	}

	public String getDes() {
		return des;
	}

	public boolean withSystem() {
		return (initParam & Type.SYSTEM.mask()) != 0;
	}

	public boolean withEmail() {
		return (initParam & Type.EMAIL.mask()) != 0;
	}

	public boolean withPhone() {
		return (initParam & Type.EMAIL.mask()) != 0;
	}

	private static final Enums[] ENUMS = Enums.values();

	public static Enums find(int type) {
		if (type < 1 || type > 6) {
			throw new RuntimeException("类型错误");
		}
		return ENUMS[type - 1];
	}


}

