import * as fs from 'fs'
import parsingDirectory from './filesfinder'

export default function parseAndroidTestResults(
  dirPath: string
): Map<string, Set<string>> {
  let files: string[] = parsingDirectory(
    dirPath,
    new RegExp('used-elements.json')
  )

  let result: Map<string, Set<string>> = new Map<string, Set<string>>()
  files.forEach(function (file: string): void {
    let json = JSON.parse(fs.readFileSync(file, 'utf-8'))
    let jsonAsMap: Map<string, string[]> = new Map(Object.entries(json))

    jsonAsMap.forEach((parameters: string[], className: string): void => {
      if (!result.has(className)) {
        result.set(className, new Set())
      }

      parameters.forEach(function (parameter: string): void {
        let set: Set<string> = result.get(className)
        set.add(parameter)
        result.set(className, set)
      })
    })
  })

  return result
}
