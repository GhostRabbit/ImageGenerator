class FunctionTree {
    constructor(f, left, right) {
        this.f = f
        this.left = left
        this.right = right
    }

    evaluate(x, y) {
        return this.f.evaluate(
            this.left ? this.left.evaluate(x, y) : x,
            this.right ? this.right.evaluate(x, y) : y
        )
    }

    static create(depth) {
        if (depth <= 1) {
            // End it.
            if (random < 0.1) {
                return new Function.Fix(random())
            }
            return
        }
        if (random() < 0.5) {
            return new FunctionTree(
                Function.randomUnary(),
                this.create(depth - 1)
            )
        } else {
            return new FunctionTree(
                Function.randomBinary(),
                this.create(depth - 1),
                this.create(depth - 1)
            )
        }
    }
}


