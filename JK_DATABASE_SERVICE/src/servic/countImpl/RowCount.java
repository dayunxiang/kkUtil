package servic.countImpl;

import java.util.List;

import enums.CountType;
import servic.Count;
import util.MyUtil;

public class RowCount implements Count {

	@Override
	public double[][] count(double[][] dataSquare, List<CountType> countTypes)
			throws Exception {
		// datasquar[4][x]
		// id
		// time
		// value1
		// value2
		double[][] re = new double[dataSquare[0].length][3];
		// re[x][3]
		// id time result
		for (int i = 0; i < dataSquare[0].length; i++) {// countTypes.size()==3
			CountType countType = countTypes.get(2);
			re[i][0] = dataSquare[0][i];
			re[i][1] = dataSquare[1][i];
			switch (countType) {
			case ADD: {
				double active_value = dataSquare[2][i];
				double passive_value = dataSquare[3][i];
				if (MyUtil.doubleValidity(active_value, passive_value)
						&& MyUtil
								.doubleCorrectness(active_value, passive_value))
					re[i][2] = active_value + passive_value;
				else
					re[i][2] = ERRORVALUE;
				break;
			}
			case SUB: {
				double active_value = dataSquare[2][i];
				double passive_value = dataSquare[3][i];
				if (MyUtil.doubleValidity(active_value, passive_value)
						&& MyUtil
								.doubleCorrectness(active_value, passive_value))
					re[i][2] = active_value - passive_value;
				else
					re[i][2] = ERRORVALUE;
				break;
			}

			case MUL: {
				double active_value = dataSquare[2][i];
				double passive_value = dataSquare[3][i];
				if (MyUtil.doubleValidity(active_value, passive_value)
						&& MyUtil
								.doubleCorrectness(active_value, passive_value))
					re[i][2] = active_value * passive_value;
				else
					re[i][2] = ERRORVALUE;
				break;
			}
			case DIV: {
				double active_value = dataSquare[2][i];
				double passive_value = dataSquare[3][i];
				if (MyUtil.doubleValidity(active_value, passive_value)
						&& MyUtil
								.doubleCorrectness(active_value, passive_value)) {
					double r_value = active_value / passive_value;
					if (MyUtil.doubleValidity(r_value))
						re[i][2] = r_value;
					else
						re[i][2] = 0;
				} else
					re[i][2] = ERRORVALUE;
				break;
			}
			default:
				re[0][i] = ERRORVALUE;
				break;
			}
		}
		return re;
	}

}
