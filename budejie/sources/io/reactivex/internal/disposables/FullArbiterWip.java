package io.reactivex.internal.disposables;

import java.util.concurrent.atomic.AtomicInteger;

class FullArbiterWip extends FullArbiterPad0 {
    final AtomicInteger wip = new AtomicInteger();

    FullArbiterWip() {
    }
}
