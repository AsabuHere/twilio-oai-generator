<?php

/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Versionless
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


namespace Twilio\Rest\Versionless\DeployedDevices;

use Twilio\Exceptions\TwilioException;
use Twilio\ListResource;
use Twilio\InstanceResource;
use Twilio\Options;
use Twilio\Stream;
use Twilio\Values;
use Twilio\Version;
use Twilio\InstanceContext;
use Twilio\Deserialize;
use Twilio\Serialize;



class FleetContext extends InstanceContext {
    /**
     * Initialize the FleetContext
     *
     * @param Version $version Version that contains the resource
     * @param string $sid 
     */
    public function __construct(Version $version, $sid ) {
        parent::__construct($version);

        // Path Solution
        $this->solution = ['sid' => $sid,  ];

        $this->uri = '/DeployedDevices/Fleets/' . \rawurlencode($sid) . '';
    }

    /**
     * Create the FleetInstance
     *
     * @param array|Options $options Optional Arguments
     * @return FleetInstance Created FleetInstance
     * @throws TwilioException When an HTTP error occurs.
     */
    public function create(array $options = []): FleetInstance {
        $options = new Values($options);

        $data = Values::of([
            'Name' => $options['name'],
        ]);

        $payload = $this->version->create('POST', $this->uri, [], $data);

        return new FleetInstance(
            $this->version,
            $payload
        );
    }

    /**
     * Fetch the FleetInstance
     *
     * @return FleetInstance Fetched FleetInstance
     * @throws TwilioException When an HTTP error occurs.
     */
    public function fetch(): FleetInstance {
        $payload = $this->version->fetch('GET', $this->uri);

        return new FleetInstance(
            $this->version,
            $payload
            , $this->solution['sid']
        );
    }

    /**
     * Provide a friendly representation
     *
     * @return string Machine friendly representation
     */
    public function __toString(): string {
        $context = [];
        foreach ($this->solution as $key => $value) {
            $context[] = "$key=$value";
        }
        return '[Twilio.Versionless.DeployedDevices.FleetContext ' . \implode(' ', $context) . ']';
    }
}