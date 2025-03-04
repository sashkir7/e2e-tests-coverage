import * as core from '@actions/core'
import parseAndroidRawSources from './parsesources'
import parseAndroidTestResults from './parsetestresults'

export async function run(): Promise<void> {
  try {
    const whoToGreet: string = core.getInput('who-to-greet', { required: true })
    core.info(`Hello, ${whoToGreet}! WELCOME!`)

    const dir = process.cwd()
    core.info(dir)

    const time: string = new Date().toTimeString()
    core.setOutput('time', time)

    justDoIt()
  } catch (error) {
    if (error instanceof Error) core.setFailed(error.message)
  }
}

function justDoIt() {
  let rawCoverage: Map<string, Set<string>> = parseAndroidRawSources(
    './sources/project/screens'
  )
  let testsCoverage: Map<string, Set<string>> = parseAndroidTestResults(
    './sources/testresults/artifact-3'
  )
  // "/Users/alx.krw/Downloads/artifact-3")

  let sumPercents: number = 0
  let percents: Map<string, number> = new Map<string, number>()
  rawCoverage.forEach((parameters: Set<string>, className: string): void => {
    let testsElements: Set<string> = new Set()
    if (testsCoverage.has(className)) {
      testsElements = testsCoverage.get(className)
    }

    let percent: number = (testsElements.size * 100) / parameters.size
    sumPercents = sumPercents + percent
    percents.set(className, percent)
  })

  let averagePercent: number = sumPercents / percents.size

  console.log(percents)
  console.log('Средний % = ' + averagePercent)

  core.info(`Средний процент ` + averagePercent)
  core.info('-------')
  core.info(JSON.stringify(Object.fromEntries(percents)))
}
