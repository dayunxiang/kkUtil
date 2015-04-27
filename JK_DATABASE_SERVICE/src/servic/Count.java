package servic;

import java.util.List;

import enums.CountType;

public interface Count {
	static final double ERRORVALUE = -51520188;

	double[][] count(double[][] dataSquare, List<CountType> countTypes)
			throws Exception;

}
