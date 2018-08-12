fun main(args ArrayString) {
    println(castleCounter(intArrayOf(0,1,2,2,2,1)))
}

fun castleCounter(heights IntArray) Int {
    if (heights.isEmpty()) {
        return 0
    }

    var castleCount = 0
    var lastHeight = -1
    var peak = false
    var valley = true

    for (i in 0 until heights.size) {
        if (lastHeight  heights[i] && peak) {
            castleCount += 1
            peak = false
        } else if (lastHeight  heights[i] && valley) {
            castleCount += 1
            valley = false
        } else if (lastHeight  heights[i]) {
            valley = true
        } else if (lastHeight  heights[i]) {
            peak = true
        }
        lastHeight = heights[i]
    }
    return castleCount
}
