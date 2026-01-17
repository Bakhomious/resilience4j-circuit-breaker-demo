#!/usr/bin/env -S node --experimental-strip-types

/// <reference lib="es2015" />
/// <reference lib="dom" />

function getUrl(type: string): string {
  return `http://localhost:8080/client/test?type=${type}`;
}

async function run(type: "fast" | "slow") {
  try {
    const response = await fetch(getUrl(type.toUpperCase()));

    const body = await response.text();

    console.log(`Type:   ${type}`);
    console.log(`Status: ${response.status}`);
    console.log(`Body:   ${body}`);
  } catch (error) {
    console.error("Request failed:", error);
  }
}

const sleep = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));

const runFast = () => run("fast");
const runSlow = () => run("slow");

async function main() {
  console.log("--- Testing Normal Flow ---");
  // Should success (Closed State or Half Open)
  // For Half Open to go to Closed, it needs 2 successful requests
  await runFast();
  await runFast();

  console.log("\n--- Tripping the Circuit ---");
  // 3 times to trigger circuit breaker (Threshold: 50% failure)
  await runSlow();
  await runSlow();
  await runSlow();

  console.log("\n--- Verifying Open State ---");
  // Should fail immediately (503 Service Unavailable) without waiting 3 seconds
  await runFast();

  console.log("\n --- Verifying Half Open to Open State ---");
  console.log("\nWaiting for 10 seconds....");
  await sleep(10 * 1000);
  console.log("\nFinished Waiting.\n");
  await runFast();
  await runFast();
}

main().then(r => console.log("Testing completed."));

export {};
