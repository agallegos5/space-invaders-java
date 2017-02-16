
public class CLThread extends Thread
{
    SpacePanel s;
    CLThread(SpacePanel si)
    {
        s = si;
    }
    
    public void run()
    {
        while(true)
        {
            s.updateChargedLasers();
            try
            {
                Thread.sleep(20);
            }catch(Exception e){}
        }
    }
}
