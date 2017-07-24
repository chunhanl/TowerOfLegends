package control;

public interface State{

	//abstract method
	public boolean shouldDo(StateControlManager manager);
	public void changeState(StateControlManager manager);
	public void showState();
}
