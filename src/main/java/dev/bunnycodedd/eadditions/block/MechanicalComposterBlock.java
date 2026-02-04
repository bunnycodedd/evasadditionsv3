package dev.bunnycodedd.eadditions.block;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import dev.bunnycodedd.eadditions.ModShapes;
import dev.bunnycodedd.eadditions.entity.block.ModBlockEntityTypes;
import dev.bunnycodedd.eadditions.entity.block.composter.MechanicalComposterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MechanicalComposterBlock extends HorizontalKineticBlock implements IBE<MechanicalComposterBlockEntity> {

    public MechanicalComposterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<MechanicalComposterBlockEntity> getBlockEntityClass() {
        return MechanicalComposterBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends MechanicalComposterBlockEntity> getBlockEntityType() {
        return ModBlockEntityTypes.MECHANICAL_COMPOSTER.get();
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(HORIZONTAL_FACING).getAxis();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        return ModShapes.BLOCK_MINUS_ONE.build();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.getValue(HORIZONTAL_FACING);
    }
}