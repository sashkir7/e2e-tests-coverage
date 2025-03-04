import * as fs from 'fs'
import parsingDirectory from './filesfinder'

export default function parseAndroidRawSources(
  dirPath: string
): Map<string, Set<string>> {
  let files: string[] = parsingDirectory(dirPath, new RegExp('.kt$'))

  let result: Map<string, Set<string>> = new Map<string, Set<string>>()
  files.forEach(function (file: string): void {
    let className: string = getClassName(file)
    let variables: Set<string> = getVariables(file)

    if (variables.size > 0) {
      result.set(className, variables)
    }
  })

  return result
}

function getClassName(filePath: string): string {
  return filePath
    .split('androidTest/java/')
    .pop()
    .split('/')
    .join('.')
    .replace('.kt', '')
}

function getVariables(filePath: string): Set<string> {
  let lines: string[] = fs.readFileSync(filePath, 'utf-8').split('\n')

  let variables: Set<string> = new Set<string>()
  let variableRegex: RegExp = /private val\s(\w+):\sK\w+/

  for (let line of lines) {
    let match = line.match(variableRegex)
    if (match) {
      let variable = match[1]
      variables.add(variable)
    }
  }

  return variables
}
