import * as fs from 'fs'
import * as path from 'path'

export default function parsingDirectory(
  dirPath: string,
  pattern: RegExp
): string[] {
  let results: string[] = []

  function traverseDirectory(dirPath: string, pattern: RegExp): void {
    let files: string[] = fs.readdirSync(dirPath)
    for (let file of files) {
      let fullPath: string = path.join(dirPath, file)
      let stat: fs.Stats = fs.statSync(fullPath)

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
