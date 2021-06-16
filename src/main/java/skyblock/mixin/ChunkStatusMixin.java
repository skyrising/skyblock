package skyblock.mixin;

import com.mojang.datafixers.util.Either;
import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ServerLightingProvider;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import skyblock.SkyBlockUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

@Mixin(ChunkStatus.class)
public class ChunkStatusMixin {
    // LIGHT
    @Inject(method = "method_20613", at = @At("HEAD"))
    private static void onLighting(ChunkStatus chunkStatus, Executor executor, ServerWorld world, ChunkGenerator generator, StructureManager manager, ServerLightingProvider lightingProvider, Function<Chunk, CompletableFuture<Either<Chunk, ChunkHolder.Unloaded>>> function, List<Chunk> list, Chunk chunk, CallbackInfoReturnable<CompletableFuture<Either<Chunk, ChunkHolder.Unloaded>>> info) {
        if(SkyBlockUtils.shouldApply(world) && !chunk.getStatus().isAtLeast(chunkStatus)) {
            SkyBlockUtils.deleteBlocks((ProtoChunk) chunk, world);
        }
    }

    // SPAWN -> populateEntities
    @Inject(method = "method_16566", at = @At("RETURN"))
    private static void afterPopulation(ChunkStatus chunkStatus, ServerWorld world, ChunkGenerator generator, List<Chunk> list, Chunk chunk, CallbackInfo info) {
        if (SkyBlockUtils.shouldApply(world)) {
            ((ProtoChunk) chunk).getEntities().clear();
        }
    }
}
