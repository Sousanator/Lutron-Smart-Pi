/**
 *  Copyright 2015 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "Lutron Pico", namespace: "njschwartz", author: "Nate Schwartz") {
		capability "Actuator"
		capability "Button"
		capability "Holdable Button"
		capability "Configuration"
		capability "Sensor"
	}
    
	tiles {
		standardTile("button", "device.button", canChangeIcon: true, inactiveLabel: false, width: 2, height: 2) {
			state "default", label: "Button", icon: "st.secondary.default", action: "push"
		}
		main "button"
		details(["button"])
	}
}

def parse(description) {
	log.debug description
	log.debug description.data.buttonNumber
    buttonEvent(description.data.buttonNumber)
}

def buttonEvent(button) {
	button = button as Integer
    log.debug "In button event " + button
    createEvent(name: "button", value: "pushed", data: [buttonNumber: button], descriptionText: "$device.displayName button $button was pushed", isStateChange: true)
}

