import * as core from '@actions/core'
import parseAndroidSources from './utils/parsesources'
import parseAndroidTestResults from './utils/parsetestresults'
import { SummaryTableCell, SummaryTableRow } from '@actions/core/lib/summary'

export async function run(): Promise<void> {
  try {
    let sourcesPath: string = core.getInput('sources-path', { required: true })
    let testsResultsPath: string = core.getInput('test-results-path', {
      required: true
    })

    await calculateCoverages(sourcesPath, testsResultsPath)
  } catch (error) {
    if (error instanceof Error) core.setFailed(error.message)
  }
}

async function calculateCoverages(
  sourcesPath: string,
  testsResultsPath: string
): Promise<void> {
  let sources: Map<string, Set<string>> = parseAndroidSources(sourcesPath)
  let testsResults: Map<string, Set<string>> = parseAndroidTestResults(
    testsResultsPath
  )

  let sumCoveragePercent: number = 0
  let coverages: [string, number][] = []
  sources.forEach((variables: Set<string>, className: string): void => {
    let testVariables: Set<string> = testsResults.has(className)
      ? testsResults.get(className)
      : new Set()
    let coveragePercent: number = (testVariables.size * 100) / variables.size
    sumCoveragePercent = sumCoveragePercent + coveragePercent
    coverages.push([className, coveragePercent])
  })

  let averagePercent: number = sumCoveragePercent / coverages.length
  coverages = coverages.sort(
    (n1: [string, number], n2: [string, number]) => n2[1] - n1[1]
  )

  await core.summary
    .addHeading(`E2E TESTS COVERAGE: ${averagePercent.toFixed(1)} %`)
    .addTable(convertToCoverageTable(coverages))
    .write()
}

function convertToCoverageTable(
  coverages: [string, number][]
): SummaryTableRow[] {
  let tableRows: SummaryTableRow[] = []
  let headers: SummaryTableCell[] = [
    { data: 'Page object class', header: true },
    { data: 'Coverage, %', header: true }
  ]

  tableRows.push(headers)
  coverages.forEach((item: [string, number]): void => {
    let row: string[] = [item[0], item[1].toFixed()]
    tableRows.push(row)
  })

  return tableRows
}
