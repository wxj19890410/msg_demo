
const STORAGE_KEY = 'app-ut-'

const localStorage = {
  save (key, value) {
    let data = {
      data: value,
      cacheTime: new Date()
    }
    window.localStorage.setItem(STORAGE_KEY + key, JSON.stringify(data))
    window.localStorage.setItem(STORAGE_KEY + key, JSON.stringify(data))
  },
  get (key) {
    return JSON.parse(window.localStorage.getItem(STORAGE_KEY + key) || '{}').data
  },
  clear (key) {
    key = key ? STORAGE_KEY + key : STORAGE_KEY
    const storage = window.localStorage
    for (let i in storage) {
      if (i.indexOf(key) === 0) {
        storage.removeItem(key)
      }
    }
  }
}
export default {
  localStorage
}
