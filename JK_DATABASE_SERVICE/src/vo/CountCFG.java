package vo;

import java.util.List;

import enums.CFGType;
import enums.CountType;

public interface CountCFG {
	CFGType getCFGType();

	String getSelectTimeFiled();

	String getResultTimeFiled();

	String getIdFiled();

	ConnectInfo getSelectConnectInfo();

	String getSelectTable();

	List<String> getSelectFiled();

	List<CountType> getCountType();

	ConnectInfo getResultConnectInfo();

	String getResultTable();

	List<String> getResultFiled();

	String getSelecteRemarkText();

	String getResultRemarkText();

	int getInterval();

	void reSet();

}
