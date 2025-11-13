
<hazelcast-client xmlns="http://www.hazelcast.com/schema/client-config"
                  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xsi:schemaLocation="http://www.hazelcast.com/schema/client-config
                                      http://www.hazelcast.com/schema/client-config/hazelcast-client-config-5.3.xsd">



    <cluster-name>${hazelcast.cluster.name}</cluster-name>

    <network>
        <cluster-members>
            <address>127.0.0.1:5701</address>
            <address>127.0.0.1:5702</address>
        </cluster-members>
    </network>

    <near-cache name="${hazelcast.near.cache}">
        <invalidate-on-change>true</invalidate-on-change>
<!--        <time-to-live-seconds>0</time-to-live-seconds>-->
    </near-cache>


</hazelcast-client>
