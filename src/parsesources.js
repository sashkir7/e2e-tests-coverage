'use strict'
Object.defineProperty(exports, '__esModule', { value: true })
exports.default = parseAndroidRawSources
var fs = require('fs')
var filesfinder_1 = require('./filesfinder')
function parseAndroidRawSources(dirPath) {
  var files = (0, filesfinder_1.default)(dirPath, new RegExp('.kt$'))
  var result = new Map()
  files.forEach(function (file) {
    var className = getClassName(file)
    var variables = getVariables(file)
    if (variables.size > 0) {
      result.set(className, variables)
    }
  })
  return result
}
function getClassName(filePath) {
  return filePath
    .split('androidTest/java/')
    .pop()
    .split('/')
    .join('.')
    .replace('.kt', '')
}
function getVariables(filePath) {
  var lines = fs.readFileSync(filePath, 'utf-8').split('\n')
  var variables = new Set()
  var variableRegex = /private val\s(\w+):\sK\w+/
  for (var _i = 0, lines_1 = lines; _i < lines_1.length; _i++) {
    var line = lines_1[_i]
    var match = line.match(variableRegex)
    if (match) {
      var variable = match[1]
      variables.add(variable)
    }
  }
  return variables
}
