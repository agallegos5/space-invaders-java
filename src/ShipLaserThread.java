
public class ShipLaserThread extends Thread
{

    SpacePanel panel;

    ShipLaserThread(SpacePanel p)
    {
            panel = p;
    }


    public void run()
    {

            while(true)
            {
                    //update the panel
                    panel.updateShipLasers();

                    //wait a few milliseconds
                    try
                    {
                            Thread.sleep(20);
                    }catch(Exception e)
                    {}
            }

    }

}
