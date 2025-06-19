package net.tefyer.eclipseallot.networking.packet;

import com.lowdragmc.lowdraglib.syncdata.payload.FriendlyBufPayload;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExampleC2SPacket {
    public ExampleC2SPacket() {
    }

    public ExampleC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(()->{
            // SERVER SIDE.
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
        });

        return true;

    }
}
