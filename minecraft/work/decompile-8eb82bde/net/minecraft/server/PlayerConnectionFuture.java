package net.minecraft.server;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

class PlayerConnectionFuture implements GenericFutureListener {

    final ChatComponentText a;
    final PlayerConnection b;

    PlayerConnectionFuture(PlayerConnection playerconnection, ChatComponentText chatcomponenttext) {
        this.b = playerconnection;
        this.a = chatcomponenttext;
    }

    public void operationComplete(Future future) {
        this.b.networkManager.close(this.a);
    }
}
