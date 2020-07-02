import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Recipe} from '../modell/recipe';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {RecipeStub} from '../modell/recipe-stub';
import {GroupStub} from '../modell/group-stub';
import {Group} from '../modell/group';

@Injectable({
  providedIn: 'root'
})
/**
 * Service für den Umgang mit Gruppen
 */
export class GroupService {

  public readonly groupURL = 'http://localhost:8080/group';

  private readonly httpOptions = {headers: new HttpHeaders({ 'Content-Type': 'application/json'})};

  constructor(
    private client: HttpClient
  ) { }

  /**
   * Fügt einen User zu einer Gruppe hinzu
   * @param stub Recipe oder GroupStub des, zu kochenden, Rezeptes
   * @param userZutaten Eingegebene Zutaten
   * @return Die ID der Gruppe
   */
  public addUserToGroup(stub: RecipeStub | GroupStub, userZutaten: string[]): Observable<number>{
    if (this.isGroupStub(stub)){
      const group = stub as GroupStub;
      return this.addToExistingGroup(group, userZutaten);
    }
    else {
      return this.createNewGroup(stub, userZutaten);
    }
  }

  /**
   * Erzeugt eine neue Gruppe für ein Rezept
   * @param rezept Stub für das Rezept
   * @param userZutaten Eingegebene Zutaten
   * @return Die ID der neuen Gruppe
   */
  public createNewGroup(rezept: RecipeStub, userZutaten: string[]){
    return this.client.post<number>(this.groupURL + `/recipe/${rezept.recipeID}`, userZutaten, this.httpOptions);
  }

  /**
   * Fügt den User zu einer bestehenden Gruppe hinzu
   * @param group Stub für die Gruppe
   * @param userZutaten Eingegebene Zutaten
   * @return Die ID der Gruppe
   */
  public addToExistingGroup(group: GroupStub, userZutaten: string[]){
    return this.client.post<number>(this.groupURL + `/${group.groupID}`, userZutaten, this.httpOptions);
  }

  /**
   * Getter für eine Gruppe
   * @param id ID der Gruppe
   * @return Die Gruppe mit der ID id
   */
  public getGroup(id: number): Observable<Group>{
    return this.client.get<Group>(this.groupURL + `/${id}`);
  }

  /**
   * Prüft ob eine RecipeStub eine GroupStub ist
   * @param recipe Zu prüfender Stub
   * @return true wenn der Stub die Eigenschaft groupID hat, ansonsten false
   */
  public isGroupStub(recipe: RecipeStub): boolean{
    return (recipe as GroupStub).groupID !== undefined;
  }
}
