package net.minecraftforge.groovy
import cpw.mods.fml.common.network.IConnectionHandler
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.network.Player
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.network.INetworkManager
import net.minecraft.network.NetLoginHandler
import net.minecraft.network.packet.NetHandler
import net.minecraft.network.packet.Packet1Login
import net.minecraft.server.MinecraftServer
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.ForgeSubscribe
import net.minecraftforge.event.entity.EntityJoinWorldEvent

class MCEventRegister {
    static void fmlEvents(GEventBus bus) {
        NetworkRegistry.instance().registerConnectionHandler(new IConnectionHandler() {
            @Override
            void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager iNetworkManager) {
                bus.dispatch(name: "fml.player.loggedin", handler: netHandler, manager: iNetworkManager, player: player)
            }

            @Override
            String connectionReceived(NetLoginHandler netLoginHandler, INetworkManager iNetworkManager) {
                bus.dispatch(name: "fml.connection.received", loginHandler: netLoginHandler, manager: iNetworkManager)
                return null
            }

            @Override
            void connectionOpened(NetHandler netHandler, String s, int i, INetworkManager iNetworkManager) {

            }

            @Override
            void connectionOpened(NetHandler netHandler, MinecraftServer minecraftServer, INetworkManager iNetworkManager) {
                bus.dispatch(name: "fml.connection.opened", handler: netHandler, server: minecraftServer, manager: iNetworkManager)
            }

            @Override
            void connectionClosed(INetworkManager iNetworkManager) {
                bus.dispatch(name: "fml.connection.closed", manager: iNetworkManager)
            }

            @Override
            void clientLoggedIn(NetHandler netHandler, INetworkManager iNetworkManager, Packet1Login packet1Login) {
                bus.dispatch(name: "fml.client.login", handler: netHandler, manager: iNetworkManager, loginPacket: packet1Login)
            }
        })
    }

    static void joinQuitEvents(GEventBus bus) {
        MinecraftForge.EVENT_BUS.register(new Object() {
            @ForgeSubscribe
            void onJoin(EntityJoinWorldEvent event) {
                if (event.entity instanceof EntityPlayer) {
                    bus.dispatch(name: "player.join", world: event.world, player: event.entity as EntityPlayer)
                }
            }
        })
    }
}