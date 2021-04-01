//
//  ViewController.swift
//  tipcalc
//
//  Created by Paul Nguyen on 11/11/17.
//  Copyright © 2017 Paul Nguyen. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var tipLabel: UILabel!
    @IBOutlet weak var totalLabel: UILabel!
    @IBOutlet weak var billField: UITextField!
    @IBOutlet weak var tipControl: UISegmentedControl!
    
    func makeGetCall() {
    // Set up the URL request
      let todoEndpoint: String = "https://jsonplaceholder.typicode.com/todos/1"
      guard let url = URL(string: todoEndpoint) else {
        print("Error: cannot create URL")
        return
      }
      let urlRequest = URLRequest(url: url)
      
      // set up the session
      let config = URLSessionConfiguration.default
      let session = URLSession(configuration: config)
      
      // make the request
      let task = session.dataTask(with: urlRequest) {
        (data, response, error) in
        // check for any errors
        guard error == nil else {
          print("error calling GET on /todos/1")
          print(error)
          return
        }
        // make sure we got data
        guard let responseData = data else {
          print("Error: did not receive data")
          return
        }
        // parse the result as JSON, since that's what the API provides
        do {
          guard let todo = try JSONSerialization.jsonObject(with: responseData, options: []) as? [String: AnyObject] else {
            print("error trying to convert data to JSON")
            return
          }
          // now we have the todo, let's just print it to prove we can access it
          print("The todo is: " + todo.description)
          
          // the todo object is a dictionary
          // so we just access the title using the "title" key
          // so check for a title and print it if we have one
          guard let todoTitle = todo["title"] as? String else {
            print("Could not get todo title from JSON")
            return
          }
          print("The title is: " + todoTitle)
        } catch  {
          print("error trying to convert data to JSON")
          return
        }
      }
      
      task.resume()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        makeGetCall()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func OnTap(_ sender: Any) {
        print( "Dismiss Keyboard" )
        view.endEditing(true)
    }
    
    @IBAction func CalculateTip(_ sender: Any) {
        
        let tipPercent = [0.18, 0.20, 0.25]
        
        let bill = Double(billField.text!) ?? 0
        let tip = bill * tipPercent[tipControl.selectedSegmentIndex]
        let total = bill + tip
        
        //tipLabel.text = "$\(tip)"
        //totalLabel.text = "$\(total)"
        tipLabel.text = String(format: "$%.2f", tip)
        totalLabel.text = String(format: "$%.2f", total)
        
        makeGetCall()
        
    }
}

