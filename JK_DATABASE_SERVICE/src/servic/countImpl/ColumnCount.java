package servic.countImpl;

import java.util.List;

import servic.Count;
import enums.CountType;

public class ColumnCount implements Count {

	@Override
	public double[][] count(double[][] dataSquare, List<CountType> countTypes)
			throws Exception {
		if (!ckeck(dataSquare, countTypes))
			throw new Exception("数据量和运算方法不符");
		double[][] re = new double[1][dataSquare.length];
		for (int i = 0; i < countTypes.size(); i++) {
			CountType countType = countTypes.get(i);
			switch (countType) {
			case AVERAGE: {
				int flg = 0;
				boolean isCon = false;
				double totle = 0;
				for (int j = 0; j < dataSquare[i].length - 1; j++) {
					if (dataSquare[i][j] == ERRORVALUE)
						continue;
					else if (dataSquare[i][j] == 0) {
						isCon |= true;
						continue;
					} else {
						isCon |= true;
						totle += dataSquare[i][j];
						flg++;
					}
				}
				double eva = 0;
				if (isCon) {
					eva = totle / flg;
					if (Double.isNaN(eva))
						eva = 0;
				} else {
					eva = ERRORVALUE;
				}
				re[0][i] = eva;
				break;
			}
			case CUMULATIVE: {// 求和
				double totle = 0;
				boolean flg = false;
				for (int j = 0; j < dataSquare[i].length - 1; j++) {
					if (dataSquare[i][j] == ERRORVALUE)
						continue;
					else {
						flg |= true;
						totle += dataSquare[i][j];
					}
				}
				if (flg)
					re[0][i] = totle * countType.getInterval();
				else
					re[0][i] = ERRORVALUE;

				break;
			}
			case RESAVE: {// 保留
				if (dataSquare[i].length >= 2) {
					re[0][i] = dataSquare[i][dataSquare[i].length - 2];
					for (double value : dataSquare[i]) {
						re[0][i] = re[0][i] < value ? value : re[0][i];
					}
				} else
					re[0][i] = ERRORVALUE;
				break;
			}
			case CUT: {// 求这个小时的最后一条数与上一个小时最后一条数的差值
				if (dataSquare[i].length >= 2) {
					double thistime = dataSquare[i][dataSquare[i].length - 2];
					double lasttime = dataSquare[i][dataSquare[i].length - 1];
					if (Double.isInfinite(lasttime) || Double.isNaN(lasttime))
						re[0][i] = 0;
					else
						re[0][i] = thistime - lasttime;
				} else
					re[0][i] = ERRORVALUE;
				break;
			}

			case GRADIENT: {// 求变化率 （值-值）/（时间-时间）
				if (dataSquare[i].length >= 2) {
					if (dataSquare.length != 1 && dataSquare.length != 0) {
						double first = dataSquare[i][dataSquare[i].length - 2];
						double last = dataSquare[i][dataSquare[i].length - 1];
						double time = dataSquare[0][dataSquare[i].length - 2]
								- dataSquare[0][dataSquare[i].length - 1];
						re[0][i] = (first - last) / (time / 60);
						/*
						 * System.out.println("last:" + last + "  first:" +
						 * first + "  timecut" + time / 60 + " rusult:" +
						 * re[0][i]);
						 */
					} else
						re[0][i] = 0;
				} else
					re[0][i] = ERRORVALUE;
				break;
			}
			case TIME: {
				re[0][i] = 0;
				break;
			}
			default:
				re[0][i] = ERRORVALUE;
				break;
			}
		}
		return re;
	}

	private boolean ckeck(double[][] dataSquare, List<CountType> countTypes) {
		return dataSquare.length == countTypes.size();
	}

}
