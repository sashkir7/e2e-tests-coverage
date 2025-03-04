'use strict'
Object.defineProperty(exports, '__esModule', { value: true })
exports.default = parseAndroidTestResults
var fs = require('fs')
var filesfinder_1 = require('./filesfinder')
function parseAndroidTestResults(dirPath) {
  var files = (0, filesfinder_1.default)(
    dirPath,
    new RegExp('used-elements.json')
  )
  var result = new Map()
  files.forEach(function (file) {
    var json = JSON.parse(fs.readFileSync(file, 'utf-8'))
    var jsonAsMap = new Map(Object.entries(json))
    jsonAsMap.forEach(function (parameters, className) {
      if (!result.has(className)) {
        result.set(className, new Set())
      }
      parameters.forEach(function (parameter) {
        var set = result.get(className)
        set.add(parameter)
        result.set(className, set)
      })
    })
  })
  return result
}
