'use strict'
Object.defineProperty(exports, '__esModule', { value: true })
exports.default = parsingDirectory
var fs = require('fs')
var path = require('path')
function parsingDirectory(dirPath, pattern) {
  var results = []
  function traverseDirectory(dirPath, pattern) {
    var files = fs.readdirSync(dirPath)
    for (var _i = 0, files_1 = files; _i < files_1.length; _i++) {
      var file = files_1[_i]
      var fullPath = path.join(dirPath, file)
      var stat = fs.statSync(fullPath)
      if (stat.isDirectory()) {
        traverseDirectory(fullPath, pattern)
      } else if (file.match(pattern)) {
        results.push(fullPath)
      }
    }
  }
  traverseDirectory(dirPath, pattern)
  return results
}
