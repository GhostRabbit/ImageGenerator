class Function {
    constructor() {
    }

    static randomUnary() {
        return random(unaryFunctions)
    }

    static randomBinary() {
        return random(binaryFunctions)
    }

    static make(f) { return { evaluate: f } }

    static Fix(v) { return this.make(() => v) }

    static colorize() {
        let r = random(255)
        let g = random(255)
        let b = random(255)
        return (v) => {
            return color(r * pow(v, 3), g * v * v, b * v)
        }
    }
}

const unaryFunctions = [
    Math.sin,
    Math.cos,
    Math.exp,
    Math.log,
    Math.sinh,
    Math.cosh,
    Math.tanh,
    (v) => -v,
    (v) => 2 * v,
    (v) => 3 * v,
].map(f => Function.make(f))

const binaryFunctions = [
    (a, b) => a + b,
    (a, b) => a - b,
    (a, b) => a * b,
    (a, b) => Math.sin(a * b),
].map(f => Function.make(f))
