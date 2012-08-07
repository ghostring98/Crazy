package de.st_ddt.crazyonline.data;

public class OnlineDataFirstLoginComperator implements OnlineDataComparator
{

	@Override
	public int compare(final OnlineData<?> o1, final OnlineData<?> o2)
	{
		return o1.getFirstLogin().compareTo(o2.getFirstLogin());
	}
}
