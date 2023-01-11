package trnrr.nosleep.mixin;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumSleepStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityPlayer.class, remap = false)
abstract class EntityPlayerMixin {
    @Shadow public abstract void addChatMessage(String s);

    @Inject(method = "sleepInBedAt",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayer;setPlayerSleeping(III)V", ordinal = 0),
            cancellable = true)
    private void disableSleep(int x, int y, int z, CallbackInfoReturnable<EnumSleepStatus> cir) {
        addChatMessage("nosleep.disabled");
        cir.setReturnValue(EnumSleepStatus.OK);
    }
}