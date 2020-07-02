import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

/**
 * Service für den Umgang mit Touch-Geräten
 */
export class TouchDeviceDetectService {

  constructor() { }

  /**
   * Prüft ob die aktuelle Umgebung Touch-Eingaben unterstützt
   */
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
