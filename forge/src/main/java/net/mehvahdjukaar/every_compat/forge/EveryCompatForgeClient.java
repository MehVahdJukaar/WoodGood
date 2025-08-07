


public class EveryCompatForgeClient {

    private static boolean firstScreenShown;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(ClientEventsForge.class);
    }

    @SubscribeEvent
    public static void onScreenDrawPost(ScreenEvent.Init.Post event) {
        if (!firstScreenShown && event.getScreen() instanceof TitleScreen) {
            ClientEvents.onFirstScreen(event.getScreen());
            firstScreenShown = true;
        }
    }
}