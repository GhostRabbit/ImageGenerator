function setup() {
    createCanvas(200, 200)
    noLoop()
    draw()
}

function makeTree(w, h) {
    while (true) {
        let tree = FunctionTree.create(8)
        let test = [
            tree.evaluate(0, 0),
            tree.evaluate(0.5, 0.5),
            tree.evaluate(0.25, 0.25),
            tree.evaluate(0.25, 0.75),
            tree.evaluate(0.75, 0.25),
            tree.evaluate(0.75, 0.75),
            tree.evaluate(1, 1),
        ]
        if (test.every(n => !isNaN(n) && isFinite(n) && n != 0)) {
            return tree
        }
    }
}

function evaluatePixels(img, fntree) {
    let n = 0
    for (let i = 0; i < img.width; i++) {
        let x = map(i, 0, img.width, 0, 1)
        for (let j = 0; j < img.height; j++) {
            let y = map(j, 0, img.height, 0, 1)
            img.pixels[n++] = fntree.evaluate(x, y)
        }
    }
}

function normalize(img) {
    let upper = Math.max(...img.pixels)
    let lower = Math.min(...img.pixels)
    img.pixels = img.pixels.map(v => map(v, upper, lower, 0, 1))
}

function makeImg(w, h) {
    const tree = makeTree(w, h)
    let iarr = {
        height: h,
        width, w,
        pixels: new Array(w * h)
    }
    evaluatePixels(iarr, tree)
    normalize(iarr)
    iarr.pixels = iarr.pixels.map(Function.colorize())

    let img = createImage(w, h)
    img.loadPixels()
    let n = 0
    for (let i = 0; i < img.width; i++) {
        for (let j = 0; j < img.height; j++) {
            img.set(i, j, iarr.pixels[n++])
        }
    }
    img.updatePixels()
    return img
}

function draw() {
    const d = 50
    for (x = 0; x < width; x += d) {
        for (y = 0; y < height; y += d) {
            image(makeImg(d, d), x, y)
        }
    }
}

function mousePressed() {
    draw()
}

