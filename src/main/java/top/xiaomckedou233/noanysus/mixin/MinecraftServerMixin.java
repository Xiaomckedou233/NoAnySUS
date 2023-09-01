package top.xiaomckedou233.noanysus.mixin;

import com.mojang.authlib.GameProfile;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.xiaomckedou233.noanysus.config.NoAnySUSConfig;

import java.util.List;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Unique
    private static final RandomSource random = RandomSource.create();

    @Shadow
    private PlayerList playerList;

    @Shadow
    public abstract boolean hidesOnlinePlayers();

    @Inject(method = "buildPlayerStatus",at = @At(value = "HEAD"), cancellable = true)
    private void injected(CallbackInfoReturnable<ServerStatus.Players> cir) {
        if(NoAnySUSConfig.isNoAnySUS()) {
            List<ServerPlayer> list = this.playerList.getPlayers();
            int i = this.playerList.getMaxPlayers();
            if (this.hidesOnlinePlayers()) {
               cir.setReturnValue(new ServerStatus.Players(i, list.size(), List.of()));
            } else {
                int j = Math.min(list.size(), 12);
                ObjectArrayList<GameProfile> objectArrayList = new ObjectArrayList<>(j);
                int k = Mth.nextInt(random, 0, list.size() - j);

                for(int l = 0; l < j; ++l) {
                    ServerPlayer serverPlayer = list.get(k + l);
                    objectArrayList.add(serverPlayer.getGameProfile());
                }
                Util.shuffle(objectArrayList, random);
                cir.setReturnValue(new ServerStatus.Players(i, list.size(), objectArrayList));
            }
        }
    }
}

