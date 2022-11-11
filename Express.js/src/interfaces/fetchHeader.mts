export interface FetchHeader {
    method: string,
    headers: {
        "Content-Type": string
    },
    body?: any
}