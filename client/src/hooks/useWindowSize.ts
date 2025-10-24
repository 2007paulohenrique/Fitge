import { useCallback, useEffect, useState } from 'react'

type DeviceType = 'desktop' | 'tablet' | 'mobile' | 'smallMobile'

type WindowSizeState = {
    width: number
    height: number
    device: DeviceType
    isMobile: boolean
    isSmallMobile: boolean
    isTablet: boolean
    isDesktop: boolean
}

export default function useWindowSize(): WindowSizeState {
    const getDeviceType = useCallback((width: number): DeviceType => {
        if (width <= 480) return 'smallMobile'

        if (width <= 768) return 'mobile'
        
        if (width <= 1024) return 'tablet'

        return 'desktop'
    }, [])

    const computeState = useCallback((width: number, height: number): WindowSizeState => {
        const device = getDeviceType(width)

        return {
            width,
            height,
            device,
            isSmallMobile: device === 'smallMobile',
            isMobile: device === 'mobile' || device === 'smallMobile',
            isTablet: device === 'tablet' || device === 'mobile' || device === 'smallMobile',
            isDesktop: device === 'desktop' || device === 'tablet' || device === 'mobile' || device === 'smallMobile'
        }
    }, [
        getDeviceType
    ])

    const [windowSize, setWindowSize] = useState<WindowSizeState>(computeState(window.innerWidth, window.innerHeight))

    useEffect(() => {
        function onResize() {
            setWindowSize(computeState(window.innerWidth, window.innerHeight))
        }

        window.addEventListener('resize', onResize)

        return () => window.removeEventListener('resize', onResize)
    }, [])

    return windowSize
}
