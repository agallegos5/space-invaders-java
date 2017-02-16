
public class ShipThread extends Thread
{
    SpacePanel s;
    ShipThread(SpacePanel si)
    {
        s = si;
    }
    
    public void run()
    {
        while(true)
        {
            s.updateShip();
            try
            {
                Thread.sleep(20);
            }catch(Exception e){}
        }
    }
}
