public class AlienThread extends Thread
{

    SpacePanel panel;

    AlienThread(SpacePanel p)
    {
            panel = p;
    }


    public void run()
    {

            while(true)
            {
                   try
                   {
                        //update the panel
                        panel.updateAliens();
                   }catch(Exception e){}

                    //wait a few milliseconds
                    try
                    {
                            Thread.sleep(20);
                    }catch(Exception e)
                    {}
            }

    }

}