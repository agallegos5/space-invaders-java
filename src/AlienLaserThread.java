
public class AlienLaserThread extends Thread
{

    SpacePanel panel;

    AlienLaserThread(SpacePanel p)
    {
            panel = p;
    }


    public void run()
    {

            while(true)
            {
                    //update the panel
                    panel.updateAlienLasers();

                    //wait a few milliseconds
                    try
                    {
                            Thread.sleep(20);
                    }catch(Exception e)
                    {}
            }

    }

}

