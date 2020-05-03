import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TouchDeviceDetectService {

  constructor() { }

  public isTouchDevice(): boolean {
    const msTouchEnabled = window.navigator.msMaxTouchPoints;
    const generalTouchEnabled = 'ontouchstart' in document.documentElement;
    let isTouch = false;
    if (msTouchEnabled || generalTouchEnabled){
      isTouch = true;
    }
    return isTouch;
  }
}
