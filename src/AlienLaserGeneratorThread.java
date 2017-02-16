
public class AlienLaserGeneratorThread extends Thread
{
    SpacePanel panel;

    AlienLaserGeneratorThread(SpacePanel p)
    {
            panel = p;
    }


    public void run()
    {

            while(true)
            {
                    //update the panel
                    panel.generateLasers();

                    //wait a few milliseconds
                    try
                    {
                            Thread.sleep(20);
                    }catch(Exception e)
                    {}
            }

    }
}
